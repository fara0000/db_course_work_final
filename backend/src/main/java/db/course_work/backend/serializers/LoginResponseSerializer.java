package db.course_work.backend.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.course_work.backend.dto.response.LoginResponse;

import java.io.IOException;

public class LoginResponseSerializer extends StdSerializer<LoginResponse> {
    public LoginResponseSerializer() {
        this(null);
    }

    public LoginResponseSerializer(Class<LoginResponse> t) {
        super(t);
    }

    @Override
    public void serialize(LoginResponse loginResponse, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField("userToken", loginResponse.getUserToken());
        jgen.writeFieldName("userData");
        jgen.writeStartObject();
        jgen.writeNumberField("id", loginResponse.getMember().getId());
        jgen.writeStringField("name", loginResponse.getMember().getName());
        jgen.writeStringField("surname", loginResponse.getMember().getSurname() == null ? loginResponse.getMember().getSurname() : "");
        jgen.writeStringField("login", loginResponse.getMember().getLogin());
        jgen.writeStringField("role", loginResponse.getMember().getRole());
        jgen.writeObjectField("synagogue", loginResponse.getMember().getSynagogue());
        jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
