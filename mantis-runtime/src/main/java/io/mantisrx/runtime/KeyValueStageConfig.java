/*
 * Copyright 2022 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mantisrx.runtime;

import io.mantisrx.common.codec.Codec;
import io.mantisrx.common.codec.Codecs;
import io.mantisrx.runtime.parameter.ParameterDefinition;
import java.util.List;

public abstract class KeyValueStageConfig<T, K, R> extends StageConfig<T, R> {

    private final Codec<K> keyCodec;

    public KeyValueStageConfig(String description, Codec<T> inputCodec, Codec<K> keyCodec, Codec<R> outputCodec, INPUT_STRATEGY inputStrategy, List<ParameterDefinition<?>> params) {
        super(description, inputCodec, outputCodec, inputStrategy, params);
        this.keyCodec = keyCodec;
    }

    public Codec<K> getOutputKeyCodec() {
        if (this.keyCodec == null) {
            return (Codec<K>) Codecs.string();
        }
        return this.keyCodec;
    }
}