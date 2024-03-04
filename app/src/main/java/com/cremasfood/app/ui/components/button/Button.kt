package com.cremasfood.app.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.type.Typography

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textColor: Color = OrangeMain,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke = BorderStroke(2.dp, OrangeMain),
    alpha: Float = 1f,
    textStyle: TextStyle = Typography.displayMedium,
    onClick: () -> Unit
) {

    OutlinedButton(
        modifier = modifier
            .height(48.dp),
        shape = shape,
        border = if (enabled) border else BorderStroke(2.dp, LightBlack),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = BackgroundColor,
            contentColor = textColor,
            disabledContentColor = LightBlack
        ),
        enabled = enabled,
        content = {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontSize = 16.sp,
                style = textStyle,
                textAlign = TextAlign.Center
            )
        },
        onClick = onClick
    )
}