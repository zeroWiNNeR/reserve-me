package com.reserveme.backend.config.datasource;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.query.sqm.function.FunctionKind;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.query.sqm.produce.function.PatternFunctionDescriptorBuilder;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class RmPostgresDialect extends PostgreSQLDialect {

    public RmPostgresDialect() {
        super(DatabaseVersion.make(10, 0));
    }

    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        super.initializeFunctionRegistry(functionContributions);

        SqmFunctionRegistry functionRegistry = functionContributions.getFunctionRegistry();
        functionRegistry.register(
                "trunc",
                new PatternFunctionDescriptorBuilder(functionRegistry,
                        "trunc",
                        FunctionKind.NORMAL,
                        "date_trunc(?1, ?2)"
                )
                        .setExactArgumentCount(2)
                        .setReturnTypeResolver(new PrimitiveFunctionTypeResolver(LocalDateTime.class))
                        .register()
        );

        functionRegistry.register(
                "to_timezone",
                new PatternFunctionDescriptorBuilder(
                        functionRegistry,
                        "to_timezone",
                        FunctionKind.NORMAL,
                        "?1 at time zone ?2"
                )
                        .setExactArgumentCount(2)
                        .setReturnTypeResolver(new PrimitiveFunctionTypeResolver(LocalDateTime.class))
                        .register()
        );
    }
}
