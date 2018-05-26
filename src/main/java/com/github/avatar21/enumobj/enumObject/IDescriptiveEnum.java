package com.github.avatar21.enumobj.enumObject;

/**
 * <p>descriptive enum type consists of code and description</p>
 *
 * sample usage:
 * <pre>
 *     <code>
 *         public enum SexEnum implements IDescriptiveEnum{@literal <}Character{@literal >} {
 *              MALE('M', "male"), FEMALE('F', "FEMALE");
 *
 *              Character code;
 *              String description;
 *
 *              SexEnum(char code, String description) {
 *                  this.code = code;
 *                  this.description = description;
 *              }
 *
 *              public void setCode(Character code) {
 *                  this.code = code;
 *              }
 *
 *              public void setDescription(String description) {
 *                  this.description = description;
 *              }
 *
 *              {@literal @}Override
 *              public Character getCode() {
 *                  return code;
 *              }
 *
 *              {@literal @}Override
 *              public String getDescription() {
 *                  return description;
 *              }
 *          }
 *     </code>
 * </pre>
 *
 * @param <K> code type
 */
public interface IDescriptiveEnum<K> {
    /**
     * <p>getter for code property</p>
     *
     * @return code of K type
     */
    K getCode();

    /**
     * <p>getter of description property</p>
     *
     * @return description
     */
    String getDescription();
}
