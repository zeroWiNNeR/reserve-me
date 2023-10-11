package com.reserveme.backend.config.datasource;

import org.hibernate.metamodel.mapping.BasicValuedMapping;
import org.hibernate.query.ReturnableType;
import org.hibernate.query.sqm.produce.function.FunctionReturnTypeResolver;
import org.hibernate.query.sqm.tree.SqmTypedNode;
import org.hibernate.sql.ast.tree.SqlAstNode;
import org.hibernate.type.spi.TypeConfiguration;

import java.util.List;
import java.util.function.Supplier;

public class PrimitiveFunctionTypeResolver implements FunctionReturnTypeResolver {

    private final Class<?> targetClass;

    public PrimitiveFunctionTypeResolver(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public ReturnableType<?> resolveFunctionReturnType(ReturnableType<?> impliedType, List<? extends SqmTypedNode<?>> arguments, TypeConfiguration typeConfiguration) {
        return typeConfiguration.getBasicTypeForJavaType(targetClass);
    }

    @Override
    public BasicValuedMapping resolveFunctionReturnType(Supplier<BasicValuedMapping> impliedTypeAccess, List<? extends SqlAstNode> arguments) {
        return impliedTypeAccess != null ? impliedTypeAccess.get() : null;
    }

}
