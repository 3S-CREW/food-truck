import * as S from '@/components/layout/EmptyList/style';
import { colors } from '@toss/tds-colors';
import { Asset, Top } from '@toss/tds-mobile';

interface Props {
  iconName: string;
  message: string;
}

const EmptyList = ({ iconName, message }: Props) => {
  return (
    <S.Container>
      <Top
        style={{ display: 'flex', alignItems: 'center' }}
        upper={
          <Top.UpperAssetContent
            content={
              <Asset.Icon
                name={iconName}
                frameShape={{
                  height: 40,
                  width: 40,
                }}
                color={colors.grey300}
              />
            }
          />
        }
        title={
          <Top.TitleParagraph
            typography='t5'
            fontWeight='regular'
            color={colors.grey500}
            textAlign='center'
          >
            {message}
          </Top.TitleParagraph>
        }
      />
    </S.Container>
  );
};

export default EmptyList;
