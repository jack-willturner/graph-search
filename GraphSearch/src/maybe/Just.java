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
 * Implementation of Just
 * (using the "composite pattern").
 */

public class Just<A> implements Maybe<A> {
  
  private final A something; 
  
  public Just(A something) { 
    this.something = something;
    // throw new Exercise("Just:Just");
  }              

  public boolean isNothing() { 
    return false;
    // throw new Exercise("Just:isNothing");
  }

  public int size() {
    return 1;    
    // throw new Exercise("Just:size");
  }

  public String toString() { 
    return "" + something;
    // throw new Exercise("Just:toString");
  }

  public boolean has(A a) {
    if(something.toString().equals(a.toString())) {
      return true;
    } 
    return false;
    // throw new Exercise("Just:has");
  }

  // Higher-order functions:

  public Maybe<A> filter(Predicate<A> p) {
	  if(p.holds(something)) {
		  return this;
	  }
	  Nothing<A> nothing = new Nothing<A>();
	  return nothing;
	  // throw new Exercise("Just:filter");
  }

  public <B> Maybe<B> map(Function<A,B> f) {
	  return new Just<B>(f.apply(something));
  }

  public <B> B fold(Function<A,B> f, B b) {
	  return f.apply(something);
	  
    // throw new Exercise("Just:fold");
  }

  public boolean all(Predicate<A> p) {
	  if (p.holds(something)) {
		  return true;
	  } 
	  return false;
    // throw new Exercise("Just:all");
  }

  public boolean some(Predicate<A> p) {
	  return p.holds(something);
	  // throw new Exercise("Just:some");
  }

  public void forEach(Action<A> f) {
	  f.apply(something);
    // throw new Exercise("Just:forEach");
  }

  // Unsafe operation:
  public A fromMaybe() {
	  return something;
  }
}
