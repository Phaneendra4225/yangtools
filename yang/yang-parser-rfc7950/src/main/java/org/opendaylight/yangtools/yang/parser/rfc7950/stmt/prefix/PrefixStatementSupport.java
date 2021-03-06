/*
 * Copyright (c) 2017 Pantheon Technologies, s.r.o. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.parser.rfc7950.stmt.prefix;

import com.google.common.collect.ImmutableList;
import org.opendaylight.yangtools.yang.model.api.YangStmtMapping;
import org.opendaylight.yangtools.yang.model.api.meta.DeclaredStatement;
import org.opendaylight.yangtools.yang.model.api.meta.EffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.PrefixEffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.PrefixStatement;
import org.opendaylight.yangtools.yang.parser.spi.meta.AbstractStringStatementSupport;
import org.opendaylight.yangtools.yang.parser.spi.meta.EffectiveStmtCtx.Current;
import org.opendaylight.yangtools.yang.parser.spi.meta.StmtContext;
import org.opendaylight.yangtools.yang.parser.spi.meta.SubstatementValidator;

public final class PrefixStatementSupport
        extends AbstractStringStatementSupport<PrefixStatement, PrefixEffectiveStatement> {
    private static final SubstatementValidator SUBSTATEMENT_VALIDATOR =
            SubstatementValidator.builder(YangStmtMapping.PREFIX).build();
    private static final PrefixStatementSupport INSTANCE = new PrefixStatementSupport();

    private PrefixStatementSupport() {
        super(YangStmtMapping.PREFIX, StatementPolicy.reject());
    }

    public static PrefixStatementSupport getInstance() {
        return INSTANCE;
    }

    @Override
    protected SubstatementValidator getSubstatementValidator() {
        return SUBSTATEMENT_VALIDATOR;
    }

    @Override
    protected PrefixStatement createDeclared(final StmtContext<String, PrefixStatement, ?> ctx,
            final ImmutableList<? extends DeclaredStatement<?>> substatements) {
        return new RegularPrefixStatement(ctx.getRawArgument(), substatements);
    }

    @Override
    protected PrefixStatement createEmptyDeclared(final StmtContext<String, PrefixStatement, ?> ctx) {
        return new EmptyPrefixStatement(ctx.getRawArgument());
    }

    @Override
    protected PrefixEffectiveStatement createEffective(final Current<String, PrefixStatement> stmt,
            final ImmutableList<? extends EffectiveStatement<?, ?>> substatements) {
        return substatements.isEmpty() ? new EmptyPrefixEffectiveStatement(stmt.declared())
            : new RegularPrefixEffectiveStatement(stmt.declared(), substatements);
    }
}
