package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDTORequestPartHttpMessageConverter implements HttpMessageConverter<GroupDTO> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return GroupDTO.class == aClass;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return GroupDTO.class == aClass;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.MULTIPART_FORM_DATA);
        return list;
    }

    @Override
    public GroupDTO read(Class<? extends GroupDTO> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream istream = httpInputMessage.getBody();
        String requestString = IOUtils.toString(istream, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(requestString, GroupDTO.class);
    }

    @Override
    public void write(GroupDTO groupDTO, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
    	httpOutputMessage.getHeaders().setContentType(mediaType);
    	ObjectMapper mapper = new ObjectMapper();
    	OutputStream outputStream = httpOutputMessage.getBody();
    	mapper.writeValue(outputStream, groupDTO);
    }
}
