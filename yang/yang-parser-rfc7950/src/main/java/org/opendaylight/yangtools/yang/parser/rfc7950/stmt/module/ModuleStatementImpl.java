/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.parser.rfc7950.stmt.module;

import com.google.common.collect.ImmutableList;
import org.opendaylight.yangtools.yang.common.UnqualifiedQName;
import org.opendaylight.yangtools.yang.model.api.meta.DeclaredStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.ModuleStatement;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.AbstractDeclaredEffectiveRootStatement;

final class ModuleStatementImpl extends AbstractDeclaredEffectiveRootStatement<ModuleStatement>
        implements ModuleStatement {
    ModuleStatementImpl(final String rawArgument, final UnqualifiedQName argument,
            final ImmutableList<? extends DeclaredStatement<?>> substatements) {
        super(rawArgument, argument, substatements);
    }
}
