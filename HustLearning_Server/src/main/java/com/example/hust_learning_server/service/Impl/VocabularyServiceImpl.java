package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.entity.*;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.Impl.VocabularyMapperImpl;
import com.example.hust_learning_server.repository.*;
import com.example.hust_learning_server.service.VocabularySerivce;
import com.example.hust_learning_server.utils.AvoidRepetition;
import com.example.hust_learning_server.utils.EmailUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularySerivce {

    private final EntityManager entityManager;

    private final VocabularyRepository vocabularyRepository;
    private final VocabularyImageRepository vocabularyImageRepository;
    private final VocabularyVideoRepository vocabularyVideoRepository;

    private final DataCollectionRepository dataCollectionRepository;
    private final TopicRepository topicRepository;
    private final VocabularyMapperImpl vocabularyMapper;

    @Override
    public VocabularyRes getById(long id) {
        Vocabulary vocabulary = vocabularyRepository.findById(id).orElseThrow(BusinessLogicException::new);

        return vocabularyMapper.toDTO(vocabulary);
    }

    @Override
    public List<VocabularyRes> getAllVocabulary() {
        List<Vocabulary> vocabularies = vocabularyRepository.findAll();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> getExactVocabularies(ExactVocabularyReq exactVocabularyReq) {
        List<Vocabulary> vocabularies = vocabularyRepository.findAllByContent(exactVocabularyReq.getContent()).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> getVocabulariesByContent(String content) {
        List<Vocabulary> vocabularies = vocabularyRepository.findAllByContent(content).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> getVocabulariesByTopicId(long topicId) {
        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesByTopicId(topicId).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> getVocabularyByTopicIdAndContent(long topicId, String content) {
        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesByTopicIdAndContent(topicId, content).orElseThrow(() -> new BusinessLogicException());
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq) {
        Pageable pageable = PageRequest.of(vocabularyLimitReq.getPage() - 1, vocabularyLimitReq.getSize());

        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesLimitByTopicId(vocabularyLimitReq.getTopicId(), pageable).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public List<VocabularyRes> vocabularyLimitsTopic(int page, int size, long topicId) {
        Pageable pageable = PageRequest.of(page - 1, size);

        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesLimitByTopicId(topicId, pageable).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public PageDTO<VocabularyRes> search(SearchVocabularyParamReq searchVocabularyParamReq) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vocabulary> criteriaQuery = criteriaBuilder.createQuery(Vocabulary.class);
        Root<Vocabulary> root = criteriaQuery.from(Vocabulary.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(searchVocabularyParamReq.text)) {
            String searchText = "%" + searchVocabularyParamReq.text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        } else return null;

        if (searchVocabularyParamReq.topicId != 0) {
            Join<Vocabulary, Topic> topicJoin = root.join("topic");
            Predicate topicLike = criteriaBuilder.equal(topicJoin.get("id"), searchVocabularyParamReq.topicId);
            predicates.add(topicLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(searchVocabularyParamReq.ascending) && !ObjectUtils.isEmpty(searchVocabularyParamReq.orderBy)) {
            if (searchVocabularyParamReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchVocabularyParamReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchVocabularyParamReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Vocabulary> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Vocabulary> results = query.setFirstResult((searchVocabularyParamReq.page - 1) * searchVocabularyParamReq.size) // Offset
                .setMaxResults(searchVocabularyParamReq.size) // Limit
                .getResultList();

        PageDTO<VocabularyRes> vocabularyResPageDTO = new PageDTO<>(vocabularyMapper.toDTOList(results), searchVocabularyParamReq.page, totalRows);
        return vocabularyResPageDTO;
    }


    @Override
    public PageDTO<VocabularyRes> search_v2(int page, int size, String text, boolean ascending, String orderBy, long topicId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vocabulary> criteriaQuery = criteriaBuilder.createQuery(Vocabulary.class);
        Root<Vocabulary> root = criteriaQuery.from(Vocabulary.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(text)) {
            String searchText = "%" + text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        } else return null;

        if (topicId != 0) {
            Join<Vocabulary, Topic> topicJoin = root.join("topic");
            Predicate topicLike = criteriaBuilder.equal(topicJoin.get("id"), topicId);
            predicates.add(topicLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(ascending) && !ObjectUtils.isEmpty(orderBy)) {
            if (ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Vocabulary> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Vocabulary> results = query.setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();

        PageDTO<VocabularyRes> vocabularyResPageDTO = new PageDTO<>(vocabularyMapper.toDTOList(results), page, totalRows);
        return vocabularyResPageDTO;
    }

    @Override
    public void addVocabulary(VocabularyReq vocabularyReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // check có topic dung ko
        Topic topic = topicRepository.findById(vocabularyReq.getTopicId()).orElseThrow(BusinessLogicException::new);

        // tu da ton tai khong luu
        Optional<Vocabulary> existingVocabulary = vocabularyRepository.findByContentAndTopicId(vocabularyReq.getContent(), vocabularyReq.getTopicId());
        if (existingVocabulary.isEmpty()) {
            Vocabulary vocabulary = vocabularyMapper.toEntity(vocabularyReq);

            // neu co image primary la true
            List<VocabularyImage> vocabularyImageList = vocabulary.getVocabularyImages();
            for (VocabularyImage image : vocabularyImageList) {
                // neu la true thi thay doi toan bo con lai ve false
                if (image.isPrimary()) {
                    List<VocabularyImage> vocabularyImageListByVocabId = vocabularyImageRepository.findAllByVocabularyId(image.getId());
                    for (VocabularyImage vocabularyImage : vocabularyImageListByVocabId) {
                        vocabularyImage.setPrimary(false);
                    }
                    vocabularyImageRepository.saveAll(vocabularyImageListByVocabId);
                }
            }

            // neu co video primary la true
            List<VocabularyVideo> vocabularyVideoList = vocabulary.getVocabularyVideos();
            for (VocabularyVideo video : vocabularyVideoList) {
                // neu la true thi thay doi toan bo con lai ve false
                if (video.isPrimary()) {
                    List<VocabularyVideo> vocabularyVideoListByVocabId = vocabularyVideoRepository.findAllByVocabularyId(video.getId());
                    for (VocabularyVideo vocabularyVideo : vocabularyVideoListByVocabId) {
                        vocabularyVideo.setPrimary(false);
                    }
                    vocabularyVideoRepository.saveAll(vocabularyVideoListByVocabId);
                }
            }

            vocabularyRepository.save(vocabulary);
        } else {
            throw new AlreadyExistsException();
        }
    }

    @Override
    public void addVocabularyToNewTopic(AddVocabularyToNewTopic addVocabularyToNewTopic) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // check có topic dung ko
        Topic topicWillAdd = topicRepository.findById(addVocabularyToNewTopic.getTopicId()).orElseThrow(BusinessLogicException::new);

        // lay ra tu o chu de hien tai
        Vocabulary vocabularyPresent = vocabularyRepository.findById(addVocabularyToNewTopic.getId()).orElseThrow(BusinessLogicException::new);

        // kiem tra topic them vao da ton tai tu nay chua, khong ton tai thi moi them vao
        Optional<Vocabulary> existingVocabularyInTopicOptional = vocabularyRepository.findByContentAndTopicId(vocabularyPresent.getContent(), addVocabularyToNewTopic.getTopicId());
        if (existingVocabularyInTopicOptional.isEmpty()) {
            // them moi vao db
            Vocabulary vocabularyWillAdd = Vocabulary.builder()
                    .content(vocabularyPresent.getContent())
                    .topic(topicWillAdd)
                    .build();
            Vocabulary vocabularyAdded = vocabularyRepository.save(vocabularyWillAdd);

            // copy toan bo VocabularyImage cua tu hien tai vao tu moi duoc tao ra
            // note: tranh luu lai chinh no se khong tao ra duoc image hoac tu moi
            List<VocabularyImage> vocabularyImageListPresent = vocabularyPresent.getVocabularyImages();
            List<VocabularyImage> vocabularyImageListWillAdd = new ArrayList<>();
            for (VocabularyImage vocabularyImagePresent : vocabularyImageListPresent) {
                VocabularyImage vocabularyImageCopy = VocabularyImage.builder()
                        .imageLocation(vocabularyImagePresent.getImageLocation())
                        .isPrimary(vocabularyImagePresent.isPrimary())
                        .vocabulary(vocabularyAdded)
                        .build();

                vocabularyImageListWillAdd.add(vocabularyImageCopy);
            }

            // copy toan bo video cua tu hien tai vao tu moi duoc tao ra
            // note: tranh luu lai chinh no se khong tao ra duoc video hoac tu moi
            List<VocabularyVideo> vocabularyVideoListPresent = vocabularyPresent.getVocabularyVideos();
            List<VocabularyVideo> vocabularyVideoListWillAdd = new ArrayList<>();
            for (VocabularyVideo vocabularyVideoPresent : vocabularyVideoListPresent) {
                VocabularyVideo vocabularyVideoCopy = VocabularyVideo.builder()
                        .videoLocation(vocabularyVideoPresent.getVideoLocation())
                        .isPrimary(vocabularyVideoPresent.isPrimary())
                        .vocabulary(vocabularyAdded)
                        .build();

                vocabularyVideoListWillAdd.add(vocabularyVideoCopy);
            }

            // them cac image/video from cu -> moi
            vocabularyImageRepository.saveAll(vocabularyImageListWillAdd);
            vocabularyVideoRepository.saveAll(vocabularyVideoListWillAdd);

            // neu chu de la 1 thi xoa tu do di
            if (vocabularyPresent.getTopic().getId() == 1) {
                vocabularyImageRepository.deleteAllByVocabularyId(addVocabularyToNewTopic.getId());
                vocabularyVideoRepository.deleteAllByVocabularyId(addVocabularyToNewTopic.getId());
                vocabularyRepository.deleteById(addVocabularyToNewTopic.getId());
            }
        } else {
            throw new AlreadyExistsException();
        }
    }

    @Override
    public void addVocabularyListToNewTopic(List<AddVocabularyToNewTopic> addVocabularyToNewTopicList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // duyet qua tung tu de xu lý
        for (AddVocabularyToNewTopic addVocabularyToNewTopic : addVocabularyToNewTopicList) {
            // check có topic và vocab dung ko, neu ko thi chuyen den tu khac
            Optional<Topic> topicWillAddOptional = topicRepository.findById(addVocabularyToNewTopic.getTopicId());
            Optional<Vocabulary> vocabularyPresentOptional = vocabularyRepository.findById(addVocabularyToNewTopic.getId());
            if (topicWillAddOptional.isPresent() && vocabularyPresentOptional.isPresent()) {
                // lay ra tu hien tai và chu de muon them vao
                Vocabulary vocabularyPresent = vocabularyPresentOptional.get();
                Topic topicWillAdd = topicWillAddOptional.get();

                // kiem tra topic them vao da ton tai tu nay chua, khong ton tai thi moi them vao
                Optional<Vocabulary> existingVocabularyInTopicOptional = vocabularyRepository.findByContentAndTopicId(vocabularyPresent.getContent(), addVocabularyToNewTopic.getTopicId());
                if (existingVocabularyInTopicOptional.isEmpty()) {
                    // them moi vao db
                    Vocabulary vocabularyWillAdd = Vocabulary.builder()
                            .content(vocabularyPresent.getContent())
                            .topic(topicWillAdd)
                            .build();
                    Vocabulary vocabularyAdded = vocabularyRepository.save(vocabularyWillAdd);

                    // copy toan bo VocabularyImage cua tu hien tai vao tu moi duoc tao ra
                    // note: tranh luu lai chinh no se khong tao ra duoc image hoac tu moi
                    List<VocabularyImage> vocabularyImageListPresent = vocabularyPresent.getVocabularyImages();
                    List<VocabularyImage> vocabularyImageListWillAdd = new ArrayList<>();
                    for (VocabularyImage vocabularyImagePresent : vocabularyImageListPresent) {
                        VocabularyImage vocabularyImageCopy = VocabularyImage.builder()
                                .imageLocation(vocabularyImagePresent.getImageLocation())
                                .isPrimary(vocabularyImagePresent.isPrimary())
                                .vocabulary(vocabularyAdded)
                                .build();

                        vocabularyImageListWillAdd.add(vocabularyImageCopy);
                    }

                    // copy toan bo video cua tu hien tai vao tu moi duoc tao ra
                    // note: tranh luu lai chinh no se khong tao ra duoc video hoac tu moi
                    List<VocabularyVideo> vocabularyVideoListPresent = vocabularyPresent.getVocabularyVideos();
                    List<VocabularyVideo> vocabularyVideoListWillAdd = new ArrayList<>();
                    for (VocabularyVideo vocabularyVideoPresent : vocabularyVideoListPresent) {
                        VocabularyVideo vocabularyVideoCopy = VocabularyVideo.builder()
                                .videoLocation(vocabularyVideoPresent.getVideoLocation())
                                .isPrimary(vocabularyVideoPresent.isPrimary())
                                .vocabulary(vocabularyAdded)
                                .build();

                        vocabularyVideoListWillAdd.add(vocabularyVideoCopy);
                    }

                    // them cac image/video from cu -> moi
                    vocabularyImageRepository.saveAll(vocabularyImageListWillAdd);
                    vocabularyVideoRepository.saveAll(vocabularyVideoListWillAdd);

                    // neu chu de la 1 thi xoa tu do di
                    if (vocabularyPresent.getTopic().getId() == 1) {
                        vocabularyImageRepository.deleteAllByVocabularyId(addVocabularyToNewTopic.getId());
                        vocabularyVideoRepository.deleteAllByVocabularyId(addVocabularyToNewTopic.getId());
                        vocabularyRepository.deleteById(addVocabularyToNewTopic.getId());
                    }
                }
            }
        }
    }

    @Override
    public void addVocabularyList(List<VocabularyReq> vocabularyReqList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // xu ly dau vao, ko bi lap lai content
        List<Vocabulary> vocabularyList = AvoidRepetition.avoidRepeatingVocabularyContent(vocabularyMapper.toEntityList(vocabularyReqList));

        // xu ly phia db, tranh bi chong lan tu
        List<Vocabulary> nonOverlappingVocabularyList = new ArrayList<>();
        for (Vocabulary vocabulary : vocabularyList) {
            // check có topic dung ko
            if (vocabulary.getTopic().getId() != 0) {
                // Kiểm tra xem từ vựng đã tồn tại trong topic chua
                Optional<Vocabulary> existingVocabulary = vocabularyRepository.findByContentAndTopicId(vocabulary.getContent(), vocabulary.getTopic().getId());

                // Nếu từ vựng không tồn tại, thêm vào danh sách không trùng lặp
                if (existingVocabulary.isEmpty()) {
                    nonOverlappingVocabularyList.add(vocabulary);

                    // neu co image primary la true
                    List<VocabularyImage> vocabularyImageList = vocabulary.getVocabularyImages();
                    for (VocabularyImage image : vocabularyImageList) {
                        // neu la true thi thay doi toan bo con lai ve false
                        if (image.isPrimary()) {
                            List<VocabularyImage> vocabularyImageListByVocabId = vocabularyImageRepository.findAllByVocabularyId(image.getId());
                            for (VocabularyImage vocabularyImage : vocabularyImageListByVocabId) {
                                vocabularyImage.setPrimary(false);
                            }
                            vocabularyImageRepository.saveAll(vocabularyImageListByVocabId);
                        }
                    }

                    // neu co video primary la true
                    List<VocabularyVideo> vocabularyVideoList = vocabulary.getVocabularyVideos();
                    for (VocabularyVideo video : vocabularyVideoList) {
                        // neu la true thi thay doi toan bo con lai ve false
                        if (video.isPrimary()) {
                            List<VocabularyVideo> vocabularyVideoListByVocabId = vocabularyVideoRepository.findAllByVocabularyId(video.getId());
                            for (VocabularyVideo vocabularyVideo : vocabularyVideoListByVocabId) {
                                vocabularyVideo.setPrimary(false);
                            }
                            vocabularyVideoRepository.saveAll(vocabularyVideoListByVocabId);
                        }
                    }
                }
            }
        }

        vocabularyRepository.saveAll(nonOverlappingVocabularyList);
    }


    @Override
    public void updateVocabulary(UpdateVocabularyReq updateVocabularyReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Vocabulary vocabulary = vocabularyRepository.findById(updateVocabularyReq.getVocabularyId()).orElseThrow(BusinessLogicException::new);
        vocabulary.setContent(updateVocabularyReq.getContent());

        vocabularyRepository.save(vocabulary);
    }

    @Override
    public void deleteById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<VocabularyImage> vocabularyImageList = vocabularyImageRepository.findAllByVocabularyId(id);
        if (!vocabularyImageList.isEmpty()) {
            vocabularyImageRepository.deleteAll(vocabularyImageList);
        }

        List<DataCollection> dataCollectionList = dataCollectionRepository.findAllByVocabularyId(id);
        if (!dataCollectionList.isEmpty()) {
            for (DataCollection dataCollection : dataCollectionList) dataCollection.setVocabulary(null);
            dataCollectionRepository.saveAll(dataCollectionList);
        }

        vocabularyRepository.deleteById(id);
    }

}
