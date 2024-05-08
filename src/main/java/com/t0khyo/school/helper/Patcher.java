package com.t0khyo.school.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Component;

@Component
public class Patcher<T> {
    private final ObjectMapper objectMapper;

    public Patcher(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Applies a JSON patch to the target entity.
     *
     * @param jsonPatch The JSON patch to apply.
     * @param target    The target entity.
     * @return The patched entity.
     * @throws JsonPatchException If there is an issue applying the patch.
     */
    public T patch(JsonPatch jsonPatch, T target) throws JsonPatchException {
        try {
            JsonNode patched = jsonPatch.apply(objectMapper.convertValue(target, JsonNode.class));
            return objectMapper.convertValue(patched, objectMapper.getTypeFactory().constructType(target.getClass()));
        } catch (JsonPatchException e) {
            throw new JsonPatchException("Failed to apply JSON patch using the patcher.", e);
        }
    }
}
