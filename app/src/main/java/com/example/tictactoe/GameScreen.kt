package com.example.tictactoe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.BlueCustom
import com.example.tictactoe.ui.theme.GrayBackground

@Composable
fun GameScreen(
    viewModel: GameViewModel
){
    val state=viewModel.state
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "Player '0': ${state.playerCircleCount}", fontSize = 16.sp)
            Text(text = "Draw '0': ${state.drawCount}", fontSize = 16.sp)
            Text(text = "Player 'X': ${state.playerCrossCount}", fontSize = 16.sp)
        }
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily= FontFamily.Cursive,
            color = BlueCustom
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ){
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
                ,
                columns = GridCells.Fixed(3)

            ) {
                    viewModel.boardItems.forEach { (cellNo,boardCellValue)->
                        item{
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        viewModel.onActions(UserActions.BoardTapped(cellNo))
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                AnimatedVisibility(
                                    visible =viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                    enter = scaleIn(tween(1000))
                                    ) {
                                    if(boardCellValue == BoardCellValue.CIRCLE){
                                        Circle()
                                    }else if (boardCellValue == BoardCellValue.CROSS){
                                        Cross()
                                    }
                                }

                            }
                        }
                    }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                ) {
                    drawVictoryLine(state = state)
                }
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text =state.playerHintText,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )
            Button(
                onClick = {
                    viewModel.onActions(
                        UserActions.PlayAgainButtonClicked
                    )
                },
                shape= RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = BlueCustom
                )
                
            ) {
                Text(text = "Play Again", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun drawVictoryLine(
    state:GameState
){
    when(state.victoryType){
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryType.VERTICAL1 -> WinVertical1()
        VictoryType.VERTICAL2 -> WinVertical2()
        VictoryType.VERTICAL3 -> WinVertical3()
        VictoryType.DIAGONAL1 -> WinTangent1()
        VictoryType.DIAGONAL2 -> WinTangent2()
        VictoryType.NONE->{}
    }
}


@Preview(showBackground = true)
@Composable
fun GameScreenPrev(){
    GameScreen(viewModel = GameViewModel())
}