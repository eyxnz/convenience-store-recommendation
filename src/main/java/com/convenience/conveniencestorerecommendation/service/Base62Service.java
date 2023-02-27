package com.convenience.conveniencestorerecommendation.service;

import io.seruco.encoding.base62.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Base62Service {
    private static final Base62 base62Instance = Base62.createInstance();

    public String encodeDirectionId(Long directionId) {
        return new String(base62Instance.encode(String.valueOf(directionId).getBytes()));
    }

    public Long decodeDirectionId(String encodedDirectionId) {
        String resultDirectionId = new String(base62Instance.decode(encodedDirectionId.getBytes()));

        return Long.valueOf(resultDirectionId);
    }
}
