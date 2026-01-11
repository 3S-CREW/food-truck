import * as S from '@/components/layout/FoodTruckCard/style';
import { colors } from '@toss/tds-colors';
import { Asset, Badge, Button, Paragraph, Text } from '@toss/tds-mobile';
import type { CSSProperties } from 'react';

export interface FoodTruckData {
  id: number;
  name: string;
  category: string;
  reportCount: number;
  status: 'exposed' | 'hidden';
}

interface Props {
  data: FoodTruckData;
}

const FoodTruckCard = ({ data }: Props) => {
  const isHidden = data.status === 'hidden';

  return (
    <S.TruckCardContainer isHidden={isHidden}>
      {/* 상단 정보 영역 */}
      <div className='top-section'>
        <div className='info-group'>
          <Asset.Icon
            frameShape={Asset.frameShape.CircleLarge}
            backgroundColor={colors.grey100}
            name='icon-truck-yellow'
            aria-hidden={true}
            ratio='1/1'
            scale={0.60}
          />

          <div className='text-info'>
            <Text typography='t4' fontWeight='bold'>
              {data.name}
            </Text>
            <Text typography='t6' color={colors.grey500}>
              {data.category}
            </Text>
          </div>
        </div>

        {/* 우측 뱃지 영역 */}
        <S.BadgeGroup>
          {isHidden && (
            <Badge size='small' color='red' variant='weak'>
              숨김 중
            </Badge>
          )}
          {data.reportCount > 0 && (
            <Badge size='small' color='yellow' variant='fill'>
              신고 {data.reportCount}건
            </Badge>
          )}
        </S.BadgeGroup>
      </div>

      {/* 하단 액션 버튼 */}
      <Button
        size='large'
        style={
          {
            width: '100%',
            marginTop: '16px',
            '--button-background-color': isHidden
              ? colors.green500
              : colors.red500,
          } as CSSProperties
        }
      >
        <Paragraph typography='t5' color={colors.white}>
          <Paragraph.Icon
            name={isHidden ? 'icon-eye-on-mono' : 'icon-eye-off-mono'}
            color={colors.white}
            style={{ marginRight: 8 }}
          />
          {isHidden ? '복구' : '숨김'}
        </Paragraph>
      </Button>
    </S.TruckCardContainer>
  );
};

export default FoodTruckCard;
