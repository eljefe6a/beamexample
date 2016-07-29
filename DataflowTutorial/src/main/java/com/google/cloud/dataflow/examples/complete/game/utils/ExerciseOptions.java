package com.google.cloud.dataflow.examples.complete.game.utils;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

import com.google.cloud.dataflow.examples.complete.game.Exercise1;

/**
 * Options supported by {@link Exercise1}.
 */
public interface ExerciseOptions extends PipelineOptions {
  @Description("BigQuery Dataset to write tables to. Must already exist.")
  @Validation.Required
  String getDataset();
  void setDataset(String value);
}
