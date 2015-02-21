// WHAT (NOT) TO DO:
// -------------------
// REPLACE the "Exercise" lines with code (possibly empty code).
// LEAVE ALONE the "Exercise" lines for parts you are not solving.
//
// To get any marks for the whole exercise, your submission should
// compile using the "compile" script. Submissions that don't compile
// won't get any marks.

package maybe;

/**
 * Implementation of Nothing
 * (using the "composite pattern").
 */

public class Nothing<A> implements Maybe<A> {
    
	
  public Nothing() { 
	  
	  // throw new Exercise("Nothing:Nothing");
  }              

  public boolean isNothing() { 
	  return true;
	  // throw new Exercise("Nothing:isNothing");
  }

  public int size() {
	  return 0;
	  // throw new Exercise("Nothing:size");
  }

  public String toString() { 
	  return "Nothing";
	  // throw new Exercise("Nothing:toString");
  }

  public boolean has(A a) {
	  return false;
	  // throw new Exercise("Nothing:has");
  }

  // Higher-order functions:

  public Maybe<A> filter(Predicate<A> p) {
	  return this;
    // throw new Exercise("Nothing:filter");
  }

  public <B> Maybe<B> map(Function<A,B> f) {
	  Nothing<B> nothing = new Nothing<B>();
	  return nothing;
   // throw new Exercise("Nothing:map");
  }

  public <B> B fold(Function<A,B> f, B b) {
	 return b;
    // throw new Exercise("Nothing:fold");
  }

  public boolean all(Predicate<A> p) {
	  return true;
    //throw new Exercise("Nothing:all");
  }

  public boolean some(Predicate<A> p) {
	  return false;
    //throw new Exercise("Nothing:some");
  }

  public void forEach(Action<A> f) {
    // throw new Exercise("Nothing:forEach");
  }

  // Unsafe operations:
  public A fromMaybe() {
    // Do nothing - return null
    A nothing = null;
    return nothing;
    //throw new Exercise("Nothing:fromMaybe");
  }
}
