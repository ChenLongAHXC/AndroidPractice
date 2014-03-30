package com.chenlong.lrc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import com.chenlong.model.LrcDocument;
import com.chenlong.model.LrcLine;

public class LrcParser {

	public LrcParser() {

	}

	public static LrcDocument parseLrcDocument(InputStream inputStream)
			throws Exception {
		LrcDocument lrcDocument = new LrcDocument();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream,"GBK"));
		String line = null;
		Pattern pattern = Pattern.compile("\\[[0-9]+:[0-9]+.[0-9]+\\]");
		while ((line = reader.readLine()) != null) {
			if (line.contains("ti:")) {
				lrcDocument.setTitle(line.substring(line.indexOf("ti:") + 3,
						line.indexOf("]")));
			} else if (line.contains("ar:")) {
				lrcDocument.setAuthor(line.substring(line.indexOf("ar:") + 3,
						line.indexOf("]")));
			} else if (pattern.matcher(line).find()) {
				LrcLine lrcLine = new LrcLine();
				String timeString = line.substring(1, line.indexOf("]"));
				String min = timeString.substring(0, 2);
				String second = timeString.substring(3, 5);
				String milli = timeString.substring(7);
				long time = Long.valueOf(min) * 60 * 1000
						+ Long.valueOf(second) * 1000 + Long.valueOf(milli)
						* 10L;
				String content = line.substring(line.indexOf("]") + 1);
				lrcLine.setTime(time);
				lrcLine.setContent(content);
				lrcDocument.getLines().add(lrcLine);
			}
		}
		return lrcDocument;
	}

}
