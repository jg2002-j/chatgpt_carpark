import java.util.regex.Pattern;

public class Validator {
  private static final String VRN_PATTERN = "^[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}$";

  public boolean validateVRN(String vrn) {
    return Pattern.matches(VRN_PATTERN, vrn.toUpperCase().replaceAll("\\s+", ""));
  }

  public String standardizeVRN(String vrn) {
    return vrn.toUpperCase().replaceAll("\\s+", "");
  }
}
