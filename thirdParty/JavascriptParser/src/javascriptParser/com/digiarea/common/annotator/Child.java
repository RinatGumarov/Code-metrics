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
package javascriptParser.com.digiarea.common.annotator;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Child.
 */
@Target({})
@Retention(RetentionPolicy.SOURCE)
public @interface Child {

	/**
	 * The Enum Link.
	 */
	enum Link {

		/** The ENCLOSED. */
		ENCLOSED,
		/** The TYPE. */
		TYPE
	}

	/**
	 * Clazz.
	 * 
	 * @return the class<? extends annotation>
	 */
	Class<? extends Annotation> clazz();

	/**
	 * Checks if is unique.
	 * 
	 * @return true, if is unique
	 */
	boolean isUnique();

	/**
	 * Link.
	 * 
	 * @return the link
	 */
	Link link() default Link.ENCLOSED;

}
