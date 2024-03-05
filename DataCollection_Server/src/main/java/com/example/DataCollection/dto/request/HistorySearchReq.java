package com.example.DataCollection.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HistorySearchReq {
    boolean isApproved;
    boolean isRejected;
}
