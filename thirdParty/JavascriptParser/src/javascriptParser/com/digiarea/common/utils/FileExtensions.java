/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
package javascriptParser.com.digiarea.common.utils;

public enum FileExtensions {

	JAVA("java"), JS("js"), CPP("cpp"), C("c"), XML("xml"), HTML(
			"html"), FXML("fxml");

	public final String EXT;
	public final String END;
	public final String ALL;

	private FileExtensions(String ext) {
		EXT = ext;
		END = "." + EXT;
		ALL = "*" + END;
	}
}
