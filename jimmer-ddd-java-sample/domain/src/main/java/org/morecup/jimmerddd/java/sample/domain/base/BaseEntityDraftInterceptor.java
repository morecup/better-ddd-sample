package org.morecup.jimmerddd.java.sample.domain.base;

import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.sql.DraftPreProcessor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import static org.morecup.jimmerddd.core.sqlclient.ImmutableSpiExtend.checkIsInsertOrUpdate;

@Component
public class BaseEntityDraftInterceptor implements DraftPreProcessor<BaseEntityDraft> {

    @Override
    public void beforeSave(BaseEntityDraft draft) {
        if (!ImmutableObjects.isLoaded(draft, BaseEntityProps.UPDATE_TIME)) {
            draft.setUpdateTime(LocalDateTime.now());
        }
        if (checkIsInsertOrUpdate(draft) && !ImmutableObjects.isLoaded(draft, BaseEntityProps.CREATE_TIME)) {
            draft.setCreateTime(LocalDateTime.now());
        }
    }

    @Override
    public boolean ignoreIdOnly() {
        return true;
    }
}