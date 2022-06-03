package sample.webhook.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@JsonTypeInfo(use=JsonTypeInfo.Id.NONE)
public class MessageModel {
    private String text;
}