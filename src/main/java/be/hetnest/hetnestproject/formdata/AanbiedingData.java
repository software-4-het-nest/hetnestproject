package be.hetnest.hetnestproject.formdata;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AanbiedingData {
    private long id=0;

    @Min(message = "Prijs mag niet negatief zijn.", value = 0)
    private double prijs;

    @NotBlank(message = "Vul een type in aub...")
    private String type;

    @NotBlank(message = "Vul een naam in aub...")
    private String naam;
}
