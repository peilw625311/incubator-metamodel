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
package org.eobjects.metamodel.intercept;

import org.eobjects.metamodel.MetaModelException;
import org.eobjects.metamodel.data.Row;
import org.eobjects.metamodel.data.Style;
import org.eobjects.metamodel.insert.RowInsertionBuilder;
import org.eobjects.metamodel.schema.Column;
import org.eobjects.metamodel.schema.Table;

final class InterceptableRowInsertionBuilder implements RowInsertionBuilder {

	private final RowInsertionBuilder _rowInsertionBuilder;
	private final InterceptorList<RowInsertionBuilder> _rowInsertionInterceptors;

	public InterceptableRowInsertionBuilder(
			RowInsertionBuilder rowInsertionBuilder,
			InterceptorList<RowInsertionBuilder> rowInsertionInterceptors) {
		_rowInsertionBuilder = rowInsertionBuilder;
		_rowInsertionInterceptors = rowInsertionInterceptors;
	}
	
	@Override
	public String toSql() {
	    return _rowInsertionBuilder.toSql();
	}

	@Override
	public RowInsertionBuilder value(int columnIndex, Object value) {
		_rowInsertionBuilder.value(columnIndex, value);
		return this;
	}

	@Override
	public RowInsertionBuilder value(int columnIndex, Object value, Style style) {
		_rowInsertionBuilder.value(columnIndex, value, style);
		return this;
	}

	@Override
	public RowInsertionBuilder value(Column column, Object value) {
		_rowInsertionBuilder.value(column, value);
		return this;
	}

	@Override
	public RowInsertionBuilder value(Column column, Object value, Style style) {
		_rowInsertionBuilder.value(column, value, style);
		return this;
	}

	@Override
	public RowInsertionBuilder value(String columnName, Object value) {
		_rowInsertionBuilder.value(columnName, value);
		return this;
	}
	
    @Override
    public RowInsertionBuilder like(Row row) {
        _rowInsertionBuilder.like(row);
        return this;
    }

	@Override
	public RowInsertionBuilder value(String columnName, Object value,
			Style style) {
		_rowInsertionBuilder.value(columnName, value, style);
		return this;
	}

	@Override
	public void execute() throws MetaModelException {
		RowInsertionBuilder rowInsertionBuilder = _rowInsertionBuilder;
		rowInsertionBuilder = _rowInsertionInterceptors
				.interceptAll(rowInsertionBuilder);
		rowInsertionBuilder.execute();
	}

	@Override
	public Row toRow() {
		return _rowInsertionBuilder.toRow();
	}

	@Override
	public Table getTable() {
		return _rowInsertionBuilder.getTable();
	}

	@Override
	public boolean isSet(Column column) {
		return _rowInsertionBuilder.isSet(column);
	}

}