/**
 * eobjects.org MetaModel
 * Copyright (C) 2010 eobjects.org
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.eobjects.metamodel.util;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a resource from which we can read and write bytes
 */
public interface Resource extends HasName {

    /**
     * Gets the name of the resource, typically a filename or other identifying
     * string
     */
    @Override
    public String getName();

    /**
     * Determines if the file is read only, or if writes are also possible.
     * 
     * @return
     */
    public boolean isReadOnly();

    /**
     * Determines if the resource referenced by this object exists or not.
     * 
     * @return
     */
    public boolean isExists();

    /**
     * Gets the size (in number of bytes) of this resource's data. An
     * approximated number is allowed.
     * 
     * If the size is not determinable without actually reading through the
     * whole contents of the resource, -1 is returned.
     * 
     * @return
     */
    public long getSize();

    /**
     * Gets the last modified timestamp value (measured in milliseconds since
     * the epoch (00:00:00 GMT, January 1, 1970)) of the resource, if available.
     * If the last modified date is not available, -1 is returned.
     * 
     * @return
     */
    public long getLastModified();

    /**
     * Opens up an {@link OutputStream} to write to the resource, and allows a
     * callback to perform writing actions on it.
     * 
     * @param writeCallback
     *            a callback which should define what to write to the resource.
     * 
     * @throws ResourceException
     *             if an error occurs while writing
     */
    public void write(Action<OutputStream> writeCallback) throws ResourceException;

    /**
     * Opens up an {@link InputStream} to append (write at the end of the
     * existing stream) to the resource.
     * 
     * @param appendCallback
     *            a callback which should define what to append to the resource.
     * @throws ResourceException
     *             if an error occurs while appending
     */
    public void append(Action<OutputStream> appendCallback) throws ResourceException;

    /**
     * Opens up an {@link InputStream} to read from the resource. Consumers of
     * this method are expected to invoke the {@link InputStream#close()} method
     * manually.
     * 
     * If possible, the other read(...) methods are preferred over this one,
     * since they guarantee proper closing of the resource's handles.
     * 
     * @return
     * @throws ResourceException
     */
    public InputStream read() throws ResourceException;

    /**
     * Opens up an {@link InputStream} to read from the resource, and allows a
     * callback to perform writing actions on it.
     * 
     * @param readCallback
     * 
     * @throws ResourceException
     *             if an error occurs while reading
     */
    public void read(Action<InputStream> readCallback) throws ResourceException;

    /**
     * Opens up an {@link InputStream} to read from the resource, and allows a
     * callback function to perform writing actions on it and return the
     * function's result.
     * 
     * @param readCallback
     * @return the result of the function
     * 
     * @throws ResourceException
     *             if an error occurs while reading
     */
    public <E> E read(Func<InputStream, E> readCallback) throws ResourceException;
}