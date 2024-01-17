import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;
import org.json.*;

public class CityDetail {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入城市名：");
			String name = scanner.nextLine();
			name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
			String api_key = "8wjuhsRb3nK5RtQl+Vjajg==ZufoKteuDPuTWVSC";
			String api_url = "https://api.api-ninjas.com/v1/city?name=" + name;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api_url)).header("X-Api-Key", api_key).GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				JSONArray jsonArray = new JSONArray(response.body());

				if (jsonArray.length() > 0) {
					JSONObject jsonObject = jsonArray.getJSONObject(0);
					String cityName = jsonObject.getString("name");
					double latitude = jsonObject.getDouble("latitude");
					double longitude = jsonObject.getDouble("longitude");
					String countryCode = jsonObject.getString("country");
					String[] countryInfo = countryCodeToFullNameAndRegion(countryCode);
					String country = countryInfo[0];
					String region = countryInfo[1];
					int population = jsonObject.getInt("population");
					boolean isCapital = jsonObject.getBoolean("is_capital");

					System.out.println("城市名: " + cityName);
					System.out.println("纬度: " + latitude);
					System.out.println("经度: " + longitude);
					System.out.println("所在国家: " + country);
					System.out.println("所在地区: " + region);
					System.out.println("人口数量: " + population);
					System.out.println("是否为首都: " + (isCapital ? "是" : "否"));
				} else {
					System.out.println("输入有误，请重新再试。");
				}
			} else {
				System.out.println("Error: " + response.statusCode() + " " + response.body());
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String[] countryCodeToFullNameAndRegion(String countryCode) {
		JSONObject countryData = new JSONObject(
				"{\"DZ\":{\"country\":\"Algeria\",\"region\":\"Africa\"},\"AO\":{\"country\":\"Angola\",\"region\":\"Africa\"},\"BJ\":{\"country\":\"Benin\",\"region\":\"Africa\"},\"BW\":{\"country\":\"Botswana\",\"region\":\"Africa\"},\"BF\":{\"country\":\"Burkina Faso\",\"region\":\"Africa\"},\"BI\":{\"country\":\"Burundi\",\"region\":\"Africa\"},\"CV\":{\"country\":\"Cabo Verde\",\"region\":\"Africa\"},\"CM\":{\"country\":\"Cameroon\",\"region\":\"Africa\"},\"CF\":{\"country\":\"Central African Republic (the)\",\"region\":\"Africa\"},\"TD\":{\"country\":\"Chad\",\"region\":\"Africa\"},\"KM\":{\"country\":\"Comoros (the)\",\"region\":\"Africa\"},\"CD\":{\"country\":\"Congo (the Democratic Republic of the)\",\"region\":\"Africa\"},\"CG\":{\"country\":\"Congo (the)\",\"region\":\"Africa\"},\"CI\":{\"country\":\"Côte d'Ivoire\",\"region\":\"Africa\"},\"DJ\":{\"country\":\"Djibouti\",\"region\":\"Africa\"},\"EG\":{\"country\":\"Egypt\",\"region\":\"Africa\"},\"GQ\":{\"country\":\"Equatorial Guinea\",\"region\":\"Africa\"},\"ER\":{\"country\":\"Eritrea\",\"region\":\"Africa\"},\"SZ\":{\"country\":\"Eswatini\",\"region\":\"Africa\"},\"ET\":{\"country\":\"Ethiopia\",\"region\":\"Africa\"},\"GA\":{\"country\":\"Gabon\",\"region\":\"Africa\"},\"GM\":{\"country\":\"Gambia (the)\",\"region\":\"Africa\"},\"GH\":{\"country\":\"Ghana\",\"region\":\"Africa\"},\"GN\":{\"country\":\"Guinea\",\"region\":\"Africa\"},\"GW\":{\"country\":\"Guinea-Bissau\",\"region\":\"Africa\"},\"KE\":{\"country\":\"Kenya\",\"region\":\"Africa\"},\"LS\":{\"country\":\"Lesotho\",\"region\":\"Africa\"},\"LR\":{\"country\":\"Liberia\",\"region\":\"Africa\"},\"LY\":{\"country\":\"Libya\",\"region\":\"Africa\"},\"MG\":{\"country\":\"Madagascar\",\"region\":\"Africa\"},\"MW\":{\"country\":\"Malawi\",\"region\":\"Africa\"},\"ML\":{\"country\":\"Mali\",\"region\":\"Africa\"},\"MR\":{\"country\":\"Mauritania\",\"region\":\"Africa\"},\"MU\":{\"country\":\"Mauritius\",\"region\":\"Africa\"},\"YT\":{\"country\":\"Mayotte\",\"region\":\"Africa\"},\"MA\":{\"country\":\"Morocco\",\"region\":\"Africa\"},\"MZ\":{\"country\":\"Mozambique\",\"region\":\"Africa\"},\"NA\":{\"country\":\"Namibia\",\"region\":\"Africa\"},\"NE\":{\"country\":\"Niger (the)\",\"region\":\"Africa\"},\"NG\":{\"country\":\"Nigeria\",\"region\":\"Africa\"},\"RE\":{\"country\":\"Réunion\",\"region\":\"Africa\"},\"RW\":{\"country\":\"Rwanda\",\"region\":\"Africa\"},\"SH\":{\"country\":\"Saint Helena, Ascension and Tristan da Cunha\",\"region\":\"Africa\"},\"ST\":{\"country\":\"Sao Tome and Principe\",\"region\":\"Africa\"},\"SN\":{\"country\":\"Senegal\",\"region\":\"Africa\"},\"SC\":{\"country\":\"Seychelles\",\"region\":\"Africa\"},\"SL\":{\"country\":\"Sierra Leone\",\"region\":\"Africa\"},\"SO\":{\"country\":\"Somalia\",\"region\":\"Africa\"},\"ZA\":{\"country\":\"South Africa\",\"region\":\"Africa\"},\"SS\":{\"country\":\"South Sudan\",\"region\":\"Africa\"},\"SD\":{\"country\":\"Sudan (the)\",\"region\":\"Africa\"},\"TZ\":{\"country\":\"Tanzania, the United Republic of\",\"region\":\"Africa\"},\"TG\":{\"country\":\"Togo\",\"region\":\"Africa\"},\"TN\":{\"country\":\"Tunisia\",\"region\":\"Africa\"},\"UG\":{\"country\":\"Uganda\",\"region\":\"Africa\"},\"EH\":{\"country\":\"Western Sahara*\",\"region\":\"Africa\"},\"ZM\":{\"country\":\"Zambia\",\"region\":\"Africa\"},\"ZW\":{\"country\":\"Zimbabwe\",\"region\":\"Africa\"},\"AQ\":{\"country\":\"Antarctica\",\"region\":\"Antarctic\"},\"BV\":{\"country\":\"Bouvet Island\",\"region\":\"Antarctic\"},\"TF\":{\"country\":\"French Southern Territories (the)\",\"region\":\"Antarctic\"},\"HM\":{\"country\":\"Heard Island and McDonald Islands\",\"region\":\"Antarctic\"},\"GS\":{\"country\":\"South Georgia and the South Sandwich Islands\",\"region\":\"Antarctic\"},\"AF\":{\"country\":\"Afghanistan\",\"region\":\"Asia\"},\"AM\":{\"country\":\"Armenia\",\"region\":\"Asia\"},\"AZ\":{\"country\":\"Azerbaijan\",\"region\":\"Asia\"},\"BD\":{\"country\":\"Bangladesh\",\"region\":\"Asia\"},\"BT\":{\"country\":\"Bhutan\",\"region\":\"Asia\"},\"IO\":{\"country\":\"British Indian Ocean Territory (the)\",\"region\":\"Asia\"},\"BN\":{\"country\":\"Brunei Darussalam\",\"region\":\"Asia\"},\"KH\":{\"country\":\"Cambodia\",\"region\":\"Asia\"},\"CN\":{\"country\":\"China\",\"region\":\"Asia\"},\"GE\":{\"country\":\"Georgia\",\"region\":\"Asia\"},\"HK\":{\"country\":\"Hong Kong\",\"region\":\"Asia\"},\"IN\":{\"country\":\"India\",\"region\":\"Asia\"},\"ID\":{\"country\":\"Indonesia\",\"region\":\"Asia\"},\"JP\":{\"country\":\"Japan\",\"region\":\"Asia\"},\"KZ\":{\"country\":\"Kazakhstan\",\"region\":\"Asia\"},\"KP\":{\"country\":\"Korea (the Democratic People's Republic of)\",\"region\":\"Asia\"},\"KR\":{\"country\":\"Korea (the Republic of)\",\"region\":\"Asia\"},\"KG\":{\"country\":\"Kyrgyzstan\",\"region\":\"Asia\"},\"LA\":{\"country\":\"Lao People's Democratic Republic (the)\",\"region\":\"Asia\"},\"MO\":{\"country\":\"Macao\",\"region\":\"Asia\"},\"MY\":{\"country\":\"Malaysia\",\"region\":\"Asia\"},\"MV\":{\"country\":\"Maldives\",\"region\":\"Asia\"},\"MN\":{\"country\":\"Mongolia\",\"region\":\"Asia\"},\"MM\":{\"country\":\"Myanmar\",\"region\":\"Asia\"},\"NP\":{\"country\":\"Nepal\",\"region\":\"Asia\"},\"PK\":{\"country\":\"Pakistan\",\"region\":\"Asia\"},\"PH\":{\"country\":\"Philippines (the)\",\"region\":\"Asia\"},\"SG\":{\"country\":\"Singapore\",\"region\":\"Asia\"},\"LK\":{\"country\":\"Sri Lanka\",\"region\":\"Asia\"},\"TW\":{\"country\":\"Taiwan (Province of China)\",\"region\":\"Asia\"},\"TJ\":{\"country\":\"Tajikistan\",\"region\":\"Asia\"},\"TH\":{\"country\":\"Thailand\",\"region\":\"Asia\"},\"TL\":{\"country\":\"Timor-Leste\",\"region\":\"Asia\"},\"TM\":{\"country\":\"Turkmenistan\",\"region\":\"Asia\"},\"UZ\":{\"country\":\"Uzbekistan\",\"region\":\"Asia\"},\"VN\":{\"country\":\"Viet Nam\",\"region\":\"Asia\"},\"BZ\":{\"country\":\"Belize\",\"region\":\"Central America\"}}");
		JSONObject countryInfo = countryData.getJSONObject(countryCode);
		String countryName = countryInfo.getString("country");
		String countryRegion = countryInfo.getString("region");
		return new String[] { countryName, countryRegion };
	}
}