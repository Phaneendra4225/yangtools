/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.model.api.type;

import java.util.List;
import java.util.Objects;
import org.opendaylight.yangtools.yang.model.api.TypeDefinition;

/**
 * Contains the method which access union item in the union type.
 */
public interface UnionTypeDefinition extends TypeDefinition<UnionTypeDefinition> {

    /**
     * Returns type definitions which represent the values of the arguments for
     * all YANG <code>type</code> substatement in the main <code>union</code>
     * statement.
     *
     * @return list of the type definition which contains the union items.
     */
    List<TypeDefinition<?>> getTypes();

    static int hashCode(final UnionTypeDefinition type) {
        return Objects.hash(type.getQName(), type.getUnknownSchemaNodes(), type.getBaseType(),
            type.getUnits().orElse(null), type.getDefaultValue().orElse(null), type.getTypes());
    }

    static boolean equals(final UnionTypeDefinition type, final Object obj) {
        if (type == obj) {
            return true;
        }

        final UnionTypeDefinition other = TypeDefinitions.castIfEquals(UnionTypeDefinition.class, type, obj);
        return other != null && type.getTypes().equals(other.getTypes());
    }

    static String toString(final UnionTypeDefinition type) {
        return TypeDefinitions.toStringHelper(type).add("types", type.getTypes()).toString();
    }
}
