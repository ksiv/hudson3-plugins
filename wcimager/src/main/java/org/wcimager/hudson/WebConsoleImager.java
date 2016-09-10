
package hudson.console;

import hudson.Extension;
import hudson.MarkupText;
import hudson.MarkupText.SubText;
import hudson.AbstractMarkupText;
import java.util.regex.Pattern;

/**
 * Annotates image URLs in the console with <img> tags.
 * supported image types (png|jpeg|jpg|gif)
 *
 * based on "UrlAnnotator" code by Kohsuke Kawaguchi 
 * @author Konstantin Ivanov
 * 
 */

 @Extension
public class WebConsoleImager extends ConsoleAnnotatorFactory<Object> {
    @Override
    public ConsoleAnnotator  newInstance(Object context) {
		// make this class abstract
        return new UrlWebConsoleImager();
    }

    private static class UrlWebConsoleImager extends ConsoleAnnotator  {
        public ConsoleAnnotator annotate(Object context, MarkupText text) {
		
            for (SubText t : text.findTokens(URL)) {
				t.wrapBy("<img src='"+t.getText()+"'>","");
            }
			
            return this;
        }

        //private static final long serialVersionUID = 1L;

        /**
         * Supported image types in regexp (png|jpeg|jpg|gif)
         */
        private static final Pattern URL = Pattern.compile("\\b(http|https|ftp)://[^\\s<>]+[^\\s<>,\\.:\"'()\\[\\]=]\\.\\b(png|jpeg|jpg|gif)");
    }
}
