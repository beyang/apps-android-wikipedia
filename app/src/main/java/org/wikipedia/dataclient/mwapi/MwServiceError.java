package org.wikipedia.dataclient.mwapi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.wikipedia.dataclient.ServiceError;

import java.util.Collections;
import java.util.List;

/**
 * Gson POJO for a MediaWiki API error.
 */
public class MwServiceError implements ServiceError {
    @SuppressWarnings("unused") @Nullable private String code;
    @SuppressWarnings("unused") @Nullable private String info;
    @SuppressWarnings("unused") @Nullable private String docref;
    @SuppressWarnings("unused") @NonNull private List<Message> messages = Collections.emptyList();

    @Override @Nullable public String getTitle() {
        return code;
    }

    @Override @Nullable public String getDetails() {
        return info;
    }

    @Nullable public String getDocRef() {
        return docref;
    }

    public boolean badToken() {
        return "badtoken".equals(code);
    }

    public boolean hasMessageName(@NonNull String messageName) {
        for (Message msg : messages) {
            if (messageName.equals(msg.name)) {
                return true;
            }
        }
        return false;
    }

    @Nullable public String getMessageHtml(@NonNull String messageName) {
        for (Message msg : messages) {
            if (messageName.equals(msg.name)) {
                return msg.html();
            }
        }
        return null;
    }

    @Override public String toString() {
        return "MwServiceError{"
                + "code='" + code + '\''
                + ", info='" + info + '\''
                + ", docref='" + docref + '\''
                + '}';
    }

    private static final class Message {
        @SuppressWarnings("unused") @Nullable private String name;
        @SuppressWarnings("unused") @Nullable private String html;

        @NonNull private String html() {
            return StringUtils.defaultString(html);
        }
    }
}
