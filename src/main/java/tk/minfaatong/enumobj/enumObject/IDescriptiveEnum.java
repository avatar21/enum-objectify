package tk.minfaatong.enumobj.enumObject;

/**
 * <p>descriptive enum type consists of code and description</p>
 * <p>
 *     sample usage:
 *     <code>
 *         public enum SexEnum implements IDescriptiveEnum<Character> {
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
 *              @Override
 *              public Character getCode() {
 *                  return code;
 *              }
 *
 *              @Override
 *              public String getDescription() {
 *                  return description;
 *              }
 *          }
 *     </code>
 * </p>
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
