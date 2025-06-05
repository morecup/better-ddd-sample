package org.morecup.jimmerddd.kotlin.sample.domain.base

import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftPreProcessor
import org.morecup.jimmerddd.core.sqlclient.checkIsInsertOrUpdate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BaseEntityDraftInterceptor : DraftPreProcessor<BaseEntityDraft> {

    /*
     * In this simple example, `BaseEntity` has only two fields: `createdTime` and `modifiedTime`.
     *
     * In actual projects, you can add more fields, such as `creator` and `modifier`,
     * and you can use the information of the permission system to set them as the current user.
     *
     * Since `DraftInterceptor` itself is a spring object, you can use any business information
     * for draft filling. This is why jimmer uses Spring-managed `DraftInterceptor` instead of
     * simply using ORM to support default value.
     */

    override fun beforeSave(draft: BaseEntityDraft) {
        if (!isLoaded(draft, BaseEntity::updateTime)) {
            draft.updateTime = LocalDateTime.now()
        }
        // `original === null` means `INSERT`
        if (checkIsInsertOrUpdate(draft)&&!isLoaded(draft, BaseEntity::createTime)){
            draft.createTime = LocalDateTime.now()
        }
    }

    override fun ignoreIdOnly(): Boolean = true
}