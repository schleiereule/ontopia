
package net.ontopia.topicmaps.entry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * INTERNAL: A convenience class that that maintains an arbitrary
 * collection of topic map references. References that are registered
 * with the source gets its source overridden. When a reference is
 * removed its source is set to null.<p>
 */

public class DefaultTopicMapSource implements TopicMapSourceIF {

  protected String id;
  protected String title;
  protected boolean hidden;

  protected Collection<TopicMapReferenceIF> refs = new HashSet<TopicMapReferenceIF>();

  public DefaultTopicMapSource() {
  }
  
  public DefaultTopicMapSource(Collection<TopicMapReferenceIF> refs) {
    Iterator<TopicMapReferenceIF> iter = refs.iterator();
    while (iter.hasNext())
      addReference(iter.next());
  }
  
  public DefaultTopicMapSource(TopicMapReferenceIF reference) {
    addReference(reference);
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  public Collection<TopicMapReferenceIF> getReferences() {
    return refs;
  }

  public void refresh() {
    // Do nothing
  }

  @Override
  public void close() {
    // Do nothing
  }

  /**
   * INTERNAL: Adds the reference to the source and registers the source
   * as the source of the reference.<p>
   *
   * @since 1.3.2
   */
  public void addReference(TopicMapReferenceIF reference) {
    reference.setSource(this);
    refs.add(reference);
  }
  
  /**
   * INTERNAL: Removes the reference from the source and deregisters the
   * source from the reference.
   *
   * @since 1.3.2
   */
  public void removeReference(TopicMapReferenceIF reference) {
    refs.remove(reference);
    reference.setSource(null);
  }

  public boolean supportsCreate() {
    return false;
  }

  public boolean supportsDelete() {
    return false;
  }

  public TopicMapReferenceIF createTopicMap(String name, String baseAddress) {
    throw new UnsupportedOperationException();
  }

  public boolean getHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }
  
}
