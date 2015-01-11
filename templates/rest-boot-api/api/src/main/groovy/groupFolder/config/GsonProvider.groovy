package ${group}.config

import org.springframework.beans.factory.annotation.Autowired

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.gson.Gson

import java.nio.charset.StandardCharsets

class GsonProvider <T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

  @Autowired
  Gson gson

  @Override
  public long getSize( T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType  ) {
    return -1;
  }

  @Override
  public boolean isWriteable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType ) {
    return true;
  }

  @Override
  public void writeTo( T object,
                       Class<?> type,
                       Type genericType,
                       Annotation[] annotations,
                       MediaType mediaType,
                       MultivaluedMap<String, Object> httpHeaders,
                       OutputStream entityStream ) throws IOException, WebApplicationException
  {
    def printWriter = new  PrintWriter(new OutputStreamWriter(
      entityStream, StandardCharsets.UTF_8), true)
    String json = gson.toJson( object )
    printWriter.write(json );
    printWriter.flush();
  }

  @Override
  public boolean isReadable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType ) {
    return true;
  }

  @Override
  public T readFrom( Class<T> type,
                     Type gnericType,
                     Annotation[] annotations,
                     MediaType mediaType,
                     MultivaluedMap<String, String> httpHeaders,
                     InputStream entityStream ) throws IOException, WebApplicationException
  {
    InputStreamReader reader = new InputStreamReader( entityStream, "UTF-8" )
    return gson.fromJson( reader, type );
  }
}
