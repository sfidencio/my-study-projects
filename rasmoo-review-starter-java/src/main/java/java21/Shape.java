package java21;

/**
 * Classes/interfaces seladas, bloqueia heranca que nao esteja declarada conforme abaixo.
 * mantem um consistencia na hierarquia de classes.
 */
public sealed interface Shape permits Square, Triangle, Rectangle, Circle {

}

