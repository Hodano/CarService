package pl.hodan.carservice.common.exception.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.hodan.carservice.common.messages.MessagesEnum;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyError {
    private String field;
    private String message;

    public MyError(String field, MessagesEnum messagesEnum) {
        this.field = field;
        this.message = messagesEnum.getCode();
    }

    public MyError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static MyError of(String field, MessagesEnum messagesEnum) {
        return new MyError(field, messagesEnum);
    }
    public static MyError of(String field, String message) {
        return new MyError(field, message);
    }
}
