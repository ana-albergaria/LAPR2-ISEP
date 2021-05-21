package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReportTest {

    //Test 1
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed(){
        Report report = new Report(null);
    }

    //Test 2
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed(){
        Report report = new Report("");
    }

    //Test 3
    @Test(expected = IllegalArgumentException.class)
    public void ensureMoreThan400WordsAreNotAllowed(){
        Report report = new Report("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vitae rutrum orci. Praesent vitae arcu urna. Ut ullamcorper non ex ullamcorper hendrerit. Phasellus convallis et dui ac tristique. Praesent id tristique sem. Sed ut sollicitudin mauris. Fusce libero enim, hendrerit et rhoncus vitae, efficitur eget arcu. Mauris et mauris congue massa venenatis venenatis. Vivamus semper varius interdum. Suspendisse efficitur, diam in efficitur imperdiet, sem nibh pharetra ante, a molestie tellus orci eget arcu. Etiam sit amet porttitor nunc. Proin vitae feugiat erat. Cras tempus elit aliquam interdum imperdiet. Proin fringilla massa ut odio varius ornare. Donec tempus, mauris quis interdum finibus, ligula quam mattis elit, eget consectetur urna nisl ut elit. Proin eget nisi scelerisque, rutrum ex sed, placerat nunc.\n" +
                "\n" +
                "Vestibulum ac diam sodales nulla interdum ultrices. Pellentesque consectetur nulla quis purus fringilla viverra. Mauris pharetra lectus laoreet erat pharetra, nec dignissim tellus blandit. Morbi sed rutrum metus, pretium semper mauris. Suspendisse in nibh nec turpis porttitor vulputate id non enim. Morbi bibendum mauris scelerisque pulvinar pulvinar. Cras fermentum, nibh in rutrum pulvinar, ipsum sapien vehicula tortor, ut suscipit sapien diam et ipsum. Integer diam massa, dignissim non aliquam at, cursus bibendum quam. Curabitur non libero metus. Aliquam ut lacus et massa sagittis luctus ac ac metus. Cras rhoncus dolor nec purus congue, a ornare purus volutpat. Pellentesque facilisis vel neque nec volutpat. Aliquam porta lorem ac leo elementum, quis hendrerit nisi vehicula.\n" +
                "\n" +
                "In hac habitasse platea dictumst. Vivamus hendrerit sapien sed urna ultrices tincidunt. Sed euismod mi in hendrerit tincidunt. Ut tincidunt facilisis ante, a suscipit nulla. Nam quam eros, blandit vitae nibh et, dictum efficitur quam. Ut sagittis in elit sed auctor. Praesent fermentum pellentesque dui, sed sagittis lorem placerat nec. Cras pretium ut ipsum eu fringilla. Curabitur aliquam sem non sapien feugiat, sed pulvinar quam tincidunt. Fusce pellentesque dolor mi, in porta mi ultrices sit amet.\n" +
                "\n" +
                "Donec molestie, ipsum vitae rutrum eleifend, arcu ante elementum ipsum, eget euismod lectus orci vel neque. Aliquam erat volutpat. Suspendisse ut ligula libero. Cras tincidunt a mauris ac interdum. Nam lobortis malesuada elit et consequat. Suspendisse iaculis lobortis augue eget sollicitudin. Pellentesque non posuere nibh, sit amet pharetra neque. Sed interdum mattis metus eu porta. Integer laoreet tristique odio, at ullamcorper elit tincidunt eu. Donec venenatis metus massa, non commodo urna dapibus ac. Aliquam aliquet, sem et pharetra tristique, enim ligula dictum diam, ut luctus arcu magna nec est. Vestibulum vehicula ornare.");
    }

}