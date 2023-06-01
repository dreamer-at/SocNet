package com.testprojects.socnet.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.testprojects.socnet.dto.PostDTO;
import com.testprojects.socnet.model.Post;
import com.testprojects.socnet.util.exception.BadRequestException;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * Provides utilities for validation of post
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

public class PostValidator {

    /**
     * Validate Author, Link, Text and Title fields and trows {@link BadRequestException}
     *
     * return true if validation will be successful
     */
    public static boolean validatePost(final PostDTO dto) {
        if (!isValidAuthorName(dto.getAuthor())) {
            throw new BadRequestException(Post.class, "Author", dto.getAuthor());
        }
        if (isValidURL(dto.getLink())) {
            throw new BadRequestException(Post.class, "Link", dto.getLink());
        }
        if (containsLink(dto.getText())) {
            if (isValidURL(dto.getText())) {
                throw new BadRequestException(Post.class, "Text", dto.getText());
            }
        }
        if (isAd_And_IsNSFW(dto)) {
            throw new BadRequestException(Post.class, "Title", dto.getTitle());
        }
        return true;
    }

    /**
     * Validate Author {@link BadRequestException}
     * Random 10 characters string containing only lowercase letters and numbers.
     * return true if validation will be successful
     */
    public static boolean isValidAuthorName(final String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[a-z0-9]{1,10}$");
    }

    /**
     * Validate URL {@link BadRequestException}
     *
     * return false if validation won't be successful
     */
    public static boolean isValidURL(final String url) {
        final var validator = new UrlValidator();
        return !validator.isValid(url);
    }

    /**
     * Validate Text {@link BadRequestException}
     *
     * return true if validation will be successful
     */
    public static boolean containsLink(String text) {
        String regexPattern = "\\b(?:https?|ftp)://[^\\s/$.?#].[^\\s]*\\b";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * Validate fields isAd and IsNSFW, they can't be true in the same time {@link BadRequestException}
     *
     * return true if validation will be successful
     */
    public static boolean isAd_And_IsNSFW(final PostDTO dto) {
        return dto.isAd() && dto.isNSFW();
    }

}
