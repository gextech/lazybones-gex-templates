package ${group}.service.impl

import org.springframework.beans.factory.annotation.Autowired

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.google.gson.Gson

import ${group}.service.XkcdService
import org.springframework.stereotype.Component

@Component
class XkcdServiceImpl implements XkcdService {

  @Autowired
  Gson gson

  @HystrixCommand(fallbackMethod="getBestComic")
  Map getLastComic(String prefix) {
    OkHttpClient client = new OkHttpClient()

    Request request = new Request.Builder()
      .url("\${prefix}/info.0.json")
      .build()
    Response response = client.newCall(request).execute()

    if(!response.isSuccessful()) {
      throw new Exception("Ugly error :( " + response.code())
    }

    gson.fromJson(response.body().string(), Map)
  }

  Map getBestComic(String prefix) {
    [
      month: "9",
      num: 162,
      link: "",
      year: "2006",
      news: "",
      safe_title: "Angular Momentum",
      transcript: "[[Man sits on his bed, looking at a girl who is spinning. It is night.]] Man on bed: What are you doing? Girl: Spinning counterclockwise Each turn robs the planet of angular momentum Slowing its spin by the tiniest bit Lengthening the night, pushing back the dawn Giving me a little more time here With you {{title text: With reasonable assumptions about latitude and body shape, how much time might she gain them? Note: whatever the answer, sunrise always comes too soon. (Also, is it worth it if she throws up?)}}",
      alt: "With reasonable assumptions about latitude and body shape, how much time might she gain them? Note: whatever the answer, sunrise always comes too soon. (Also, is it worth it if she throws up?)",
      img: "http://imgs.xkcd.com/comics/angular_momentum.jpg",
      title: "Angular Momentum",
      day: "25"
    ]
  }

}

