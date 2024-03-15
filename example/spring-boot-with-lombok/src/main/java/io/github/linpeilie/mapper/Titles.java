package io.github.linpeilie.mapper;

import org.mapstruct.Named;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Component;

@Component
@Named("TitleTranslator")
public class Titles {

    @Named("EnglishToFrench")
    public String translateTitleEF(String title) {
        if ("One Hundred Years of Solitude".equals(title)) {
            return "Cent ans de solitude";
        }
        return "Inconnu et inconnu";
    }

    @Named("FrenchToEnglish")
    public String translateTitleFE(String title) {
        if ("Cent ans de solitude".equals(title)) {
            return "One Hundred Years of Solitude";
        }
        return "Unknown";
    }

}
