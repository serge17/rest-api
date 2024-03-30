package org.company.extension

import org.company.request.GetUsersRequest
import org.springframework.data.domain.PageRequest

fun GetUsersRequest.toPageRequest(): PageRequest {
    return PageRequest.of(this.pageNumber, this.pageSize)
}
