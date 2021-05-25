package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

// idea from https://stackoverflow.com/questions/22899363/advice-on-class-structure-in-a-collision-detection-system
// based on this https://refactoring.guru/design-patterns/visitor design pattern
public abstract class BodyCollider {
    protected Element element;
    protected BoundingBox boundingBox;
    private short categoryMask;
    private ColliderCategory category;

    protected BodyCollider(Element element, short categoryMask, ColliderCategory category) {
        this.element = element;
        this.boundingBox = null;
        this.categoryMask = categoryMask;
        this.category = category;
    }

    protected BodyCollider(Element element) {
        this(element, (short) -1, ColliderCategory.GENERAL);
    }

    public BoundingBox getBoundingBox() {
        if(this.boundingBox == null) this.boundingBox = createBoundingBox();
        return boundingBox;
    }

    abstract protected BoundingBox createBoundingBox();

    public boolean collides(BodyCollider other) {
        if((this.categoryMask & other.category.getBits()) == 0 || (other.categoryMask & this.category.getBits()) == 0) {
            return false;
        }

        if(!this.getBoundingBox().collides(
                other.getBoundingBox(), this.element.getPosition(), other.element.getPosition())
        ) {
            return false;
        }
        else return innerVisit(other);
    }

    public Element getElement() {
        return element;
    }

    public void setCategoryMask(short categoryMask) {
        this.categoryMask = categoryMask;
    }

    public void setCategory(ColliderCategory category) {
        this.category = category;
    }

    abstract protected boolean innerVisit(BodyCollider other);
    abstract protected boolean collidesLine(LineCollider other);
    abstract protected boolean collidesLineComposite(LineCompositeCollider other);

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BodyCollider)) {
            return false;
        }

        BodyCollider other = (BodyCollider) o;

        // Compare the data members and return accordingly
        return this.element.equals(other.element)
                && category.equals(other.category) && categoryMask == other.categoryMask;
    }
}
