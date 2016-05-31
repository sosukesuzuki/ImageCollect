package example

import org.jsoup._
import collection.JavaConverters._
import java.io.{FileOutputStream, BufferedOutputStream}
import java.net.URL
import scala.language.postfixOps

object Main {
    def main(args: Array[String]): Unit = {
        val url;
        val doc = Jsoup.connect(url).get
        val images = doc.getElementsByTag("img")
        var roop = 1
        for(t <- images.asScala) {
            val imagePath = t.attr("src")
            val filename = "ero-gazo" + roop + ".jpg"
            roop += 1
            download(imagePath, filename)
        }
    }

    def download(imagePath: String, filename: String){
        val stream = new URL(imagePath).openStream
        val buf = Stream.continually(stream.read).takeWhile( -1 != ).map(_.byteValue).toArray
        val bw = new BufferedOutputStream(new FileOutputStream(filename))
        bw.write(buf)
        bw.close
    }
}
