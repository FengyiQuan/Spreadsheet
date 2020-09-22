package edu.cs3500.spreadsheets.model;

/**
 * An runtime exception which represents in the worksheet there is some circular reference would
 * cause stack over flow somewhere. To avoid this happen, throw {@code CircularReferenceException}.
 */
public class CircularReferenceException extends RuntimeException {
}
