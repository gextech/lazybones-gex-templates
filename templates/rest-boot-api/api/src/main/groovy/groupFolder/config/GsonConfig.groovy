package ${group}.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type

@Configuration
class GsonConfig {

  @Bean
  Gson buildGson() {
    Gson gson =  new GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      // use ISO 8601 formatted date
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
      .serializeNulls()
      .create()

    gson
  }

}
