/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

$(document).ready(function () {

    //#selectAllBoxes er den id vi gav vores <th><input id="selectAllBoxes" type="checkbox"></th>
    $('#selectAllBoxes').click(function (event) {

        if (this.checked) {

            //' .checkBoxes' er den klasse i satte i echo "<td><input class='checkBoxes' type='checkbox' name='checkboxArray[]' value='{$post_id}'></td>";
            $('.checkBoxes').each(function () {

                //Sætter alle checkbokse med .checkBoxes til true
                this.checked = true;

            });

        } else {

            //' .checkBoxes' er den klasse i satte i echo "<td><input class='checkBoxes' type='checkbox' name='checkboxArray[]' value='{$post_id}'></td>";
            $('.checkBoxes').each(function () {

                //Sætter alle checkbokse med .checkBoxes til false
                this.checked = false;

            });
        }
    });
});