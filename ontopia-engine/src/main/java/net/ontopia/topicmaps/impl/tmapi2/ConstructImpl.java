/*
 * #!
 * Ontopia Engine
 * #-
 * Copyright (C) 2001 - 2013 The Ontopia Project
 * #-
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * !#
 */

package net.ontopia.topicmaps.impl.tmapi2;

import java.util.Set;

import net.ontopia.topicmaps.core.ConstraintViolationException;
import net.ontopia.topicmaps.core.TMObjectIF;

import org.tmapi.core.Construct;
import org.tmapi.core.IdentityConstraintException;
import org.tmapi.core.Locator;

/**
 * INTERNAL: OKS->TMAPI 2 object wrapper.
 */

abstract class ConstructImpl implements Construct {

  protected TopicMapImpl topicMap;

  public ConstructImpl(TopicMapImpl topicMap) {
    this.topicMap = topicMap;
  }

  protected abstract TMObjectIF getWrapped();

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#getId()
   */
  
  @Override
  public String getId() {
    return getWrapped().getObjectId();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#getItemIdentifiers()
   */
  
  @Override
  public Set<Locator> getItemIdentifiers() {
    return topicMap.wrapSet(getWrapped().getItemIdentifiers());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#addItemIdentifier(org.tmapi.core.Locator)
   */
  
  @Override
  public void addItemIdentifier(Locator iid) {
    Check.itemIdentifierNotNull(this, iid);
    try {
      getWrapped().addItemIdentifier(topicMap.unwrapLocator(iid));
    } catch (ConstraintViolationException ex) {
      throw new IdentityConstraintException(this, topicMap.getConstructByItemIdentifier(iid), iid,
          "A construct with the same item identifier exists already");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#removeItemIdentifier(org.tmapi.core.Locator)
   */
  
  @Override
  public void removeItemIdentifier(Locator iid) {
    getWrapped().removeItemIdentifier(topicMap.unwrapLocator(iid));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#getTopicMap()
   */
  
  @Override
  public TopicMapImpl getTopicMap() {
    return topicMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tmapi.core.Construct#remove()
   */
  
  @Override
  public void remove() {
    getWrapped().remove();
  }

  
  @Override
  public boolean equals(Object obj) {
    if ((obj!=null) && (obj instanceof ConstructImpl)) {
    	return getWrapped() == ((ConstructImpl) obj).getWrapped();
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return getWrapped().hashCode();
  }

}
