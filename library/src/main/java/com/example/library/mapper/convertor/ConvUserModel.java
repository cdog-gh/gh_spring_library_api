package com.example.library.mapper.convertor;
import com.example.library.model.User.User;
import com.example.library.model.User.UserLoginInfo;
import com.example.library.model.User.UserRegInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper
public interface ConvUserModel {
    ConvUserModel instance = Mappers.getMapper(ConvUserModel.class);

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "userEmail", target = "userEmail")
    @Mapping(
        source = "userPw", target = "userPw",
        qualifiedByName = "encryptUserPw"
    )
    User UserRegInfoToUser(UserRegInfo target);

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "userPw", target = "userPw")
    User UserLoginInfoToUser(UserLoginInfo target);

    @Named("encryptUserPw")
    default String encryptUserPw(String pw){
        return new BCryptPasswordEncoder().encode(pw);
    }
}
