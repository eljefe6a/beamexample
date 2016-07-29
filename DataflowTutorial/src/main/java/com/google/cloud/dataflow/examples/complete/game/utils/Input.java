/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.dataflow.examples.complete.game.utils;

import com.google.cloud.dataflow.examples.complete.game.GameActionInfo;
import com.google.cloud.dataflow.examples.complete.game.injector.InjectorBoundedSource;
import com.google.cloud.dataflow.examples.complete.game.injector.InjectorUnboundedSource;
import com.google.cloud.dataflow.sdk.io.Read;
import com.google.cloud.dataflow.sdk.transforms.PTransform;
import com.google.cloud.dataflow.sdk.values.PBegin;
import com.google.cloud.dataflow.sdk.values.PCollection;

/**
 * Helpers for generating the input
 */
public class Input {

  /**
   * Generate a bounded {link PCollection} of data.
   */
  public static class BoundedGenerator extends PTransform<PBegin, PCollection<GameActionInfo>> {
    @Override
    public PCollection<GameActionInfo> apply(PBegin input) {
      return input.apply(Read.from(new InjectorBoundedSource(1_000_000, 180, 200)));
    }
  }

  /**
   * Generate an unbounded {@link PCollection} of data.
   */
  public static class UnboundedGenerator extends PTransform<PBegin, PCollection<GameActionInfo>> {
    @Override
    public PCollection<GameActionInfo> apply(PBegin input) {
      return input.apply(Read.from(new InjectorUnboundedSource()));
    }
  }
}
