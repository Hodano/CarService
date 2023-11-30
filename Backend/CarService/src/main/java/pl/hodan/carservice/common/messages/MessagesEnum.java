package pl.hodan.carservice.common.messages;

import lombok.Getter;

public enum MessagesEnum {
    BAD_EMAIL(removeBracelets(Messages.BAD_EMAIL)),
    EMPTY_FIELD(removeBracelets(Messages.EMPTY_FIELD)),
    EXIST_EMAIL(removeBracelets(Messages.EXIST_EMAIL)),
    PHONE_NUMBER_IS_NULL(removeBracelets(Messages.PHONE_NUMBER_IS_NULL)),
    INVALID_EMAIL_OR_PASSWORD(removeBracelets(Messages.INVALID_EMAIL_OR_PASSWORD));

    MessagesEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    private static String removeBracelets(String errorText){
        return errorText.replace("{", "").replace("}", "");
    }

    @Override
    public String toString() {
        return "MessagesEnum{" +
                "code='" + code + '\'' +
                '}';
    }
}
