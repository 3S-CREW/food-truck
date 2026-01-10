import { Asset, Text } from '@toss/tds-mobile';
import { colors } from '@toss/tds-colors';
import * as S from './style';

interface PageHeaderProps {
  title: string;
  subtitle?: string;
}

const PageHeader = ({ title, subtitle }: PageHeaderProps) => {
  const titleLines = title.split('\n');

  return (
    <S.ContentWrapper>
      <Asset.Frame
        shape={{ width: 100, height: 100 }}
        content={
          <Asset.ContentImage
            src="https://static.toss.im/3d-emojis/u1F69A-apng.png"
            alt="트럭 이모지"
          />
        }
      />
      <S.TextGroup>
        <div style={{ textAlign: 'center' }}>
          {titleLines.map((line, index) => (
            <Text key={index} typography="t1" fontWeight="bold" display="block">
              {line}
            </Text>
          ))}
        </div>
        {subtitle && (
          <div style={{ marginTop: '12px' }}>
            <Text color={colors.grey500} typography="t5">
              {subtitle}
            </Text>
          </div>
        )}
      </S.TextGroup>
    </S.ContentWrapper>
  );
};

export default PageHeader;
