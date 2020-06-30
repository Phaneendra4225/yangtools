/*
 * Copyright (c) 2017 Pantheon Technologies, s.r.o. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.parser.rfc7950.stmt.when;

import com.google.common.collect.ImmutableList;
import org.opendaylight.yangtools.yang.model.api.RevisionAwareXPath;
import org.opendaylight.yangtools.yang.model.api.YangStmtMapping;
import org.opendaylight.yangtools.yang.model.api.meta.DeclaredStatement;
import org.opendaylight.yangtools.yang.model.api.meta.EffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.WhenEffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.WhenStatement;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.ArgumentUtils;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.BaseStatementSupport;
import org.opendaylight.yangtools.yang.parser.spi.meta.StmtContext;
import org.opendaylight.yangtools.yang.parser.spi.meta.SubstatementValidator;

public final class WhenStatementSupport
        extends BaseStatementSupport<RevisionAwareXPath, WhenStatement, WhenEffectiveStatement> {
    private static final SubstatementValidator SUBSTATEMENT_VALIDATOR = SubstatementValidator.builder(
        YangStmtMapping.WHEN)
        .addOptional(YangStmtMapping.DESCRIPTION)
        .addOptional(YangStmtMapping.REFERENCE)
        .build();
    private static final WhenStatementSupport INSTANCE = new WhenStatementSupport();

    private WhenStatementSupport() {
        super(YangStmtMapping.WHEN);
    }

    public static WhenStatementSupport getInstance() {
        return INSTANCE;
    }

    @Override
    public RevisionAwareXPath parseArgumentValue(final StmtContext<?, ?, ?> ctx, final String value) {
        return ArgumentUtils.parseXPath(ctx, value);
    }

    @Override
    protected SubstatementValidator getSubstatementValidator() {
        return SUBSTATEMENT_VALIDATOR;
    }

    @Override
    protected WhenStatement createDeclared(final StmtContext<RevisionAwareXPath, WhenStatement, ?> ctx,
            final ImmutableList<? extends DeclaredStatement<?>> substatements) {
        return new RegularWhenStatement(ctx, substatements);
    }

    @Override
    protected WhenStatement createEmptyDeclared(final StmtContext<RevisionAwareXPath, WhenStatement, ?> ctx) {
        return new EmptyWhenStatement(ctx);
    }

    @Override
    protected WhenEffectiveStatement createEffective(
            final StmtContext<RevisionAwareXPath, WhenStatement, WhenEffectiveStatement> ctx,
            final WhenStatement declared, final ImmutableList<? extends EffectiveStatement<?, ?>> substatements) {
        return new RegularWhenEffectiveStatement(declared, substatements);
    }

    @Override
    protected WhenEffectiveStatement createEmptyEffective(
            final StmtContext<RevisionAwareXPath, WhenStatement, WhenEffectiveStatement> ctx,
            final WhenStatement declared) {
        return new EmptyWhenEffectiveStatement(declared);
    }
}
