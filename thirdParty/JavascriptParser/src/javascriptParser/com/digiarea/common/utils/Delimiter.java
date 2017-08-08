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

/**
 * The Enum InnerDelimiter.
 */
public enum Delimiter {

	/** The DOLLAR. */
	DOLLAR("$"),
	/** The UNDERSCORE. */
	UNDERSCORE("_"),
	/** The DOT. */
	DOT("."),
	/** The NONE. */
	NONE(" "),
	/** The EMPTY. */
	EMPTY("empty");

	/** The delimiter. */
	private String delimiter;

	/**
	 * Instantiates a new inner delimiter.
	 * 
	 * @param delimiter
	 *            the delimiter
	 */
	private Delimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return delimiter;
	}

}