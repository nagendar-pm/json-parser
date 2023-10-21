/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:01 pm
 */

package com.nagendar.learning.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
	public static String toAbsolutePath(String maybeRelative) {
		Path path = Paths.get(maybeRelative);
		Path effectivePath = path;
		if (!path.isAbsolute()) {
			Path base = Paths.get("");
			effectivePath = base.resolve(path).toAbsolutePath();
		}
		return effectivePath.normalize().toString();
	}

	public static Set<String> listFilesUsingFilesList(String dir) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
			return stream
					.filter(file -> !Files.isDirectory(file))
					.map(Path::toString)
					.collect(Collectors.toSet());
		}
	}
}
